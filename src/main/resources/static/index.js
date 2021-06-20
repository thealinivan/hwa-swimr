'use strict';

//state
const clubs = [];
const output = document.getElementById("render-club");

//API - read all
const getClubs = async () => {
    const res = await axios.get("/clubs/readAll");
    output.innerHTML = "";
    res.data.reverse().forEach(club => {
        clubs.push(club);
        renderClub(club)
    });
}

//count places for a club
const clubPlaces = (club) => {
    let count = 0;
    for (const place of club.places) {
        count++;
    }
    return count;
}

//render a club
const renderClub = (club) => {


    const card = document.createElement('div');
    card.className = "card-container";

    const row = document.createElement('div');
    row.classList.add('row', 'align-self-center');
    card.appendChild(row);

    const grid1 = document.createElement('div');
    grid1.classList.add('col-sm-12', 'col-md-9', 'col-lg-10');
    row.appendChild(grid1);

    const link = document.createElement('a');
    link.href = `./place.html?id=${club.id}`;
    grid1.appendChild(link);

    const title = document.createElement('h3');
    title.innerText = club.name;
    link.appendChild(title);

    const subtitle = document.createElement('p');
    subtitle.innerText = `Swimming places: ${clubPlaces(club)}`;
    link.appendChild(subtitle);

    const grid2 = document.createElement('div');
    grid2.classList.add('col-sm-12', 'col-md-3', 'col-lg-2', 'text-center');
    row.appendChild(grid2);

    const grid31 = document.createElement('div');
    grid31.classList.add('col-sm-12', 'card-el', 'text-center', 'card-btn');
    grid2.appendChild(grid31);

    const delBtn = document.createElement('button');
    delBtn.classList.add('btn', 'btn-outline-danger', 'card-el');
    delBtn.type = 'submit';
    delBtn.innerText = 'Delete';
    delBtn.addEventListener('click', function () { deleteClub(club.id) })
    grid31.append(delBtn);

    const grid32 = document.createElement('div');
    grid32.classList.add('col-sm-12', 'card-el', 'text-center', 'card-btn');
    grid2.appendChild(grid32);

    const updateBtn = `
            <button type="submit" data-bs-toggle="modal" data-id=${club.id} data-bs-target="#btn-update-club"
                class="btn btn-outline-warning card-el">Update</button>
    `
    $(grid32).append(updateBtn);

    document.getElementById('render-club').appendChild(card);
}

//delete a club
const deleteClub = async (id) => {
    const res = await axios.delete(`/clubs/delete/${id}`);
    getClubs();
};

//search
const search = () => {
    output.innerHTML = "";
    let filtered = clubs.filter(club => club.name.toLowerCase()
        .includes($('#field-search').val().toLowerCase()));
    filtered.forEach(club => {
        renderClub(club);
    })
}

//on DOM ready
$(document).ready(function () {

    //render all clubs
    getClubs();

    //create a club
    document.getElementById("club-form").addEventListener("submit", function (event) {
        event.preventDefault();

        const data = {
            name: this.name.value
        }

        axios.post("/clubs/create", data)
            .then(res => {
                getClubs();
                this.reset();
            }).catch(err => console.log(err));
        // window.location.reload();
    });

    //update a club
    document.getElementById('btn-update-club').addEventListener('show.bs.modal', function (event) {
        let id = $(event.relatedTarget).data('id');
        clubs.forEach(club => {
            if (club.id === id) {
                document.getElementById("update-club-name").value = club.name;
            }
        })
        document.getElementById("update-form").addEventListener('submit', function (event) {
            event.preventDefault();
            const data = {
                name: this.name.value
            }
            axios.put(`/clubs/update/${id}`, data)
                .then(res => {
                    getClubs();
                    this.reset();
                    $('#btn-update-club').modal('hide');
                }).catch(err => console.log(err));
        })
    })

    //search place on keyup
    document.getElementById('field-search').addEventListener('keyup', (event) => {
        event.preventDefault();
        console.log("keyup");
        search();
    })

    //search place on search button click
    document.getElementById("btn-search").addEventListener('click', (event) => {
        event.preventDefault();
        search();
    })

});