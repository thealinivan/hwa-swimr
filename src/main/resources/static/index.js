
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
//..

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
//...

//CLUBS
//...

//GET DATA FROM FORMS
//PLACES
//...

//CLUBS
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


