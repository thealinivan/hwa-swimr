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

//count places for a club
const clubPlaces = (club) => {
    let count = 0;
    for (const place of club.places) {
        count++;
    }
    return count;
}

const renderPlace = (place) => {
    const card = document.createElement('div');
    card.className = "card-container";

    const row = document.createElement('div');
    row.classList.add('row', 'align-self-center');
    card.appendChild(row);

    const grid1 = document.createElement('div');
    grid1.classList.add('col-sm-12', 'col-md-4', 'col-lg-4', 'text-center');
    grid1.id = 'card-map';
    row.appendChild(grid1);

    const map = document.createElement('div');
    map.classList.add('card-img');
    map.id = `map-${place.id}`;
    grid1.appendChild(map);

    const grid2 = document.createElement('div');
    grid2.classList.add('col-sm-12', 'col-md-5', 'col-lg-6');
    row.appendChild(grid2);

    const bodyContainer = document.createElement('div');
    grid2.appendChild(bodyContainer);

    const grid21 = document.createElement('div');
    grid21.classList.add('col-sm-12', 'body-el');
    bodyContainer.appendChild(grid21);

    const title = document.createElement('h3');
    title.id = 'card-name';
    title.innerHTML = place.name;
    grid21.appendChild(title);

    const grid22 = document.createElement('div');
    grid22.classList.add('row', 'align-self-center');
    bodyContainer.appendChild(grid22);

    const grid221 = document.createElement('div');
    grid221.classList.add('col-sm-3', 'col-md-6', 'col-lg-2', 'text-center');
    grid22.appendChild(grid221);

    const icon = document.createElement('img');
    icon.classList.add('card-el-icon');
    icon.src = 'img/icon_pin.png';
    icon.alt = 'ballon icon for place';
    grid221.appendChild(icon);

    const grid222 = document.createElement('div');
    grid222.classList.add('col-sm-3', 'col-md-4', 'col-lg-3', 'text-center', 'body-el');
    grid222.id = 'card-postcode';
    grid222.innerHTML = place.postcode;
    grid22.appendChild(grid222);

    const btnsGrid = document.createElement('div');
    btnsGrid.classList.add('col-sm-12', 'col-md-3', 'col-lg-2', 'text-center');
    row.appendChild(btnsGrid);

    const btnGrid1 = document.createElement('div');
    btnGrid1.classList.add('col-sm-12', 'card-el', 'text-center', 'card-btn');
    btnsGrid.appendChild(btnGrid1);

    const delBtn = document.createElement('button');
    delBtn.classList.add('btn', 'btn-outline-danger', 'card-el');
    delBtn.type = 'submit';
    delBtn.innerText = 'Delete';
    delBtn.addEventListener('click', function () { deletePlace(place.id) })
    btnGrid1.append(delBtn);

    const btnGrid2 = document.createElement('div');
    btnGrid2.classList.add('col-sm-12', 'card-el', 'text-center', 'card-btn');
    btnsGrid.appendChild(btnGrid2);

    const updateBtn = `
            <button type="submit" data-bs-toggle="modal" data-id=${place.id} data-bs-target="#btn-update"
                        class="btn btn-outline-warning card-el">Update</button>
    `
    $(btnsGrid).append(updateBtn);

    output.appendChild(card);
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


