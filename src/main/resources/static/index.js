'use strict';

const output = document.getElementById("render-club");

const getClubs = async () => {
    const res = await axios.get("/clubs/readAll");
    output.innerHTML = "";
    res.data.reverse().forEach(club => renderClub(club));
}

const renderClub = (club) => {


    const card = `
            <div class="card-container">
                <div class="row align-self-center">
                    <div class="col-sm-12 col-md-9 col-lg-10 ">
                        <a href="./place.html">
                            <h3 id="card-name">${club.name}</h3>
                        </a>
                    </div>
                    <div class="col-sm-12 col-md-3 col-lg-2 text-center">
                        <div class="col-sm-12 card-el text-center card-btn">
                            <button type="submit" onClick="deleteClub(${club.id})" class="btn btn-outline-danger card-el"
                                id='card-delete-btn'>Delete</button>
                        </div>
                        <div class="col-sm-12 card-el text-center card-btn">
                            <button type="submit" data-bs-toggle="modal" data-id=${club.id} data-bs-target="#btn-update-club"
                                class="btn btn-outline-warning card-el">Update</button>
                        </div>
                    </div>

                </div>
            </div>
        `;

    $('#render-club').append(card);
}

getClubs();

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
    window.location.reload();
});

const deleteClub = async (id) => {
    const res = await axios.delete(`/clubs/delete/${id}`);
    getClubs();
};

document.getElementById('btn-update-club').addEventListener('show.bs.modal', function (event) {
    let id = $(event.relatedTarget).data('id');
    console.log(id);
    //get modal field and populate from places obj array using id
})

document.getElementById("card-name").addEventListener('click', () => {
    //local storage or other method to send the id to place.html
});