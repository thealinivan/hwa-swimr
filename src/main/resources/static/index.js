
//API

//MAPS(POSTCODES) API
//render map to for a place
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
                console.log(TARGET);
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
//Place API 
const places = [
    {
        id: 0,
        name: "Hackney Leisure Centre",
        postcode: "SE2 9XA",
        type: "Indoor"
    },
    {
        id: 1,
        name: "Eastham swimming pool",
        postcode: "E12 6LB",
        type: "Outdoor"
    }
];

//Create place fetch
//...
//Read place fetch
//...
//Update place fetch
//...
//Delete place fetch
//...


//CLUBS API
//Create club fetch
//...
//Read clubs fetch
//...
//Update club(assign place) fetch
//...
//Update club(remove place) fetch
//Delete club fetch
//...


//RENDER DATA TO DOM
//PLACES

//Render a place
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

                                <!-- card-type -->
                                <div class="row align-self-center">
                                    <div class="col-sm-3 col-md-6 col-lg-2 text-center">
                                        <img class="card-el-icon" src="img/icon_pool.png" alt="Pool icon">
                                    </div>
                                    <div class="col-sm-3 col-md-4 col-lg-3 text-center body-el" id="card-type">
                                        ${place.type}
                                    </div>
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
                                <button type="submit" class="btn btn-outline-danger card-el"
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
//Read places to DOM
function readPlacesToDOM(places) {
    places.forEach(p => {
        renderPlace(p);
    })
}

//CLUBS
//...

//Read clubs to DOM
function readClubsToDOM(clubs) {
    clubs.forEach(c => {
        renderClub(c);
    })
}

//GET DATA FROM FORMS
//PLACES
//Add a place form
function getDataFromForm() {
    const pName = document.getElementById("add-name").value;
    const pPostcode = document.getElementById("add-postcode").value;
    const pType = document.getElementById("add-select").value;

    const place = {
        id: 1,
        name: pName,
        postcode: pPostcode,
        type: pType,
        clubId: null
    }
    console.log("nameascas: " + place.name);
    return place;
}

//CLUBS
//Add a club form
//...



//ON DOCUMENT LOAD
$(window).bind("load", function () {

    //EVENT LISTENERS

    //SEARCH
    //...

    //PLACES
    //..


    //CLUBS
    //Create club
    //...
    //Update (asign place)
    //...
    //Remove (from club)
    //...
    //Delete
    //...

})


