'use strict';

//Maps API
function getMap(POSTCODE, TARGET) {
    const POSTCODES_URL = "https://api.postcodes.io/postcodes/"

    //get map to dom
    fetch(POSTCODES_URL + POSTCODE)
        .then(response => response.json())
        .then(data => {
            const coord = {
                lat: data.result.latitude,
                lng: data.result.longitude
            }
            function initMap(coord) {
                const map = new google.maps.Map(TARGET, {
                    center: coord,
                    zoom: 16,
                    disableDefaultUI: true,
                });
                new google.maps.Marker({
                    position: coord,
                    map,
                });
            }
            initMap(coord);
        })
}

//state
const currentClubId = new URLSearchParams(window.location.search).get("id");
let currentClub = {};
const output = document.getElementById("render");
const places = [];

//API - read all
const getPlaces = async () => {
    const res = await axios.get(`/clubs/read/${currentClubId}`);
    output.innerHTML = "";
    currentClub = {
        id: res.data.id,
        name: res.data.name
    }
    document.getElementById("place-title").innerHTML = res.data.name;
    res.data.places.reverse().forEach(place => {
        places.push(place);
        renderPlace(place);
    })
}

const renderPlace = (place) => {
    const card = `
                <div class="card-container">
                    <div class="row align-self-center">

                        <!-- img -->
                        <div class="col-sm-12 col-md-4 col-lg-4 text-center" id="card-map">
                             <div id='map-${place.id}' class="card-img"></div>
                        </div>

                        <!-- body -->
                        <div class="col-sm-12 col-md-5 col-lg-6 ">
                            <div class="container">

                                <!-- card-title -->
                                <div class="col-sm-12 body-el">
                                    <h3 id="card-name">${place.name}</h3>
                                </div>

                                <!-- card-postcode -->
                                <div class="row align-self-center">
                                    <div class="col-sm-3 col-md-6 col-lg-2 text-center">
                                        <img class="card-el-icon" src="img/icon_pin.png" alt="pin icon">
                                    </div>
                                    <div class="col-sm-3 col-md-4 col-lg-3 text-center body-el" id="card-postcode">
                                         ${place.postcode}
                                    </div>
                                </div>

                            </div>
                        </div>

                        <!-- buttons -->
                        <div class="col-sm-12 col-md-3 col-lg-2 text-center">

                            <div class="col-sm-12 card-el text-center card-btn">
                                <button type="submit" onClick="deletePlace(${place.id})"class="btn btn-outline-danger card-el"
                                    id='card-delete-btn'>Delete</button>
                            </div>
                            <div class="col-sm-12 card-el text-center card-btn">
                                <button type="submit" data-bs-toggle="modal" data-id=${place.id} data-bs-target="#btn-update"
                                    class="btn btn-outline-warning card-el">Update</button>
                            </div>
                        </div>

                    </div>
                </div>
        `;

    $("#render").append(card);
    getMap(place.postcode, document.getElementById(`map-${place.id}`));
}

//delete a place
const deletePlace = async (id) => {
    const res = await axios.delete(`/places/delete/${id}`);
    getPlaces();
};

//search
const search = () => {
    output.innerHTML = "";
    let filtered = places.filter(place => place.name.toLowerCase()
        .includes($('#field-search').val().toLowerCase()));
    filtered.forEach(place => {
        renderPlace(place);
    })
}

//on DOM ready
$(document).ready(function () {

    //render all places for the current club
    getPlaces();

    //create a place
    document.getElementById("add-form").addEventListener("submit", function (event) {
        event.preventDefault();
        const data = {
            name: this.name.value,
            postcode: this.postcode.value,
            club: currentClub
        }
        axios.post("/places/create", data)
            .then(res => {
                getPlaces();
                this.reset();
            }).catch(err => console.log(err));
    });

    //update a place
    document.getElementById('btn-update').addEventListener('show.bs.modal', function (event) {
        let id = $(event.relatedTarget).data('id');
        places.forEach(place => {
            if (place.id === id) {
                document.getElementById("update-name").value = place.name;
                document.getElementById("update-postcode").value = place.postcode;
            }
        })
        document.getElementById("update-form").addEventListener('submit', function (event) {
            event.preventDefault();
            const data = {
                name: this.name.value,
                postcode: this.postcode.value
            }
            axios.put(`/places/update/${id}`, data)
                .then(res => {
                    getPlaces();
                    this.reset();
                    $('#btn-update').modal('hide');
                }).catch(err => console.log(err));
        })
    })

    //search place on keyup
    document.getElementById('field-search').addEventListener('keyup', (event) => {
        event.preventDefault();
        search();
    })

    //search place on search button click
    document.getElementById("btn-search").addEventListener('click', (event) => {
        event.preventDefault();
        search();
    })

});


