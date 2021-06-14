
//API

//MAPS(POSTCODES) API
//Render map to for a place
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


//PLACES API
//Places Array
const places = [
    {
        id: 1,
        name: "Hackney Leisure Centre",
        postcode: "SE2 9XA"
    },
    {
        id: 2,
        name: "Eastham swimming pool",
        postcode: "E12 6LB"
    }
];

//Create place fetch
function createPlace(place) {
    axios.post("/places/create", place)
        .then(res => {
            //readAllPlaces();
            renderPlace(place);
            //empty form
        }).catch(err => console.log(err));
}

//Read place fetch
//...

//Update place fetch
//...

//Delete place fetch
function deletePlace(clickedId) {
    const id = clickedId;
    console.log(`deletePlace: ${id}`)
}


//RENDER

//Place card
function renderPlace(place) {
    const card = `
                <div class="card-container" id=${place.id}>
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

//Read places list
function readPlacesToDOM(places) {
    places.forEach(p => {
        renderPlace(p);
    })
}

//FORMS

//Add a place form
function getDataFromAddForm() {
    const pName = document.getElementById("add-name").value;
    const pPostcode = document.getElementById("add-postcode").value;

    const place = {
        id: 1,
        name: pName,
        postcode: pPostcode
    }
    return place;
}

//Update a place form
function getDataFromUpdateForm() {
    const pName = document.getElementById("update-name").value;
    const pPostcode = document.getElementById("update-postcode").value;

    const place = {
        name: pName,
        postcode: pPostcode
    }
    console.log(place);
    return place;
}

//ON DOCUMENT LOAD
$(window).bind("load", function () {

    //EVENT LISTENERS

    //SEARCH
    //...

    //Create place
    $("#add-form").submit((event) => {
        event.preventDefault();
        const addedPlace = getDataFromAddForm();
        createPlace(addedPlace);

    })

    //Read places
    readPlacesToDOM(places);

    //Update place: open modal
    document.getElementById('btn-update').addEventListener('show.bs.modal', function (event) {
        let id = $(event.relatedTarget).data('id');
        console.log(id);
        //get modal field and populate from places obj array using id
    })

    //Update place: submit
    document.getElementById("update-btn").addEventListener('click', () => {
        const updatedPlace = getDataFromUpdateForm();
        renderPlace(updatedPlace);
    })

    //Delete place
    //..is handled on the html onclick event


})


