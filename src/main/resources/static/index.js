'use strict';

const output = document.getElementById("render-club");

const getClubs = async () => {
    const res = await axios.get("/clubs/");
    output.innerHTML = "";
    res.data.forEach(club => renderClub(club));
}

const renderClub = (club) => {
    const card = document.createElement("div");
    column.className = "col";
  
    const card = document.createElement("div");
    card.className = "card";
    column.appendChild(card);

    const cardBody = document.createElement("div");
    cardBody.className = "card-body";
    card.appendChild(cardBody);

    const makeText = document.createElement("p");
    makeText.className = "card-text";
    makeText.innerText = `Club Name: ${club.name}`;
    cardBody.appendChild(makeText);

    const cardFooter = document.createElement("div");
    cardFooter.className = "card-footer";
    card.appendChild(cardFooter);

    const deleteButton = document.createElement("a");
    deleteButton.innerText = "Delete";
    deleteButton.className = "card-link";
    deleteButton.addEventListener("click", function () {
        deleteClub(id);
    });
    cardFooter.appendChild(deleteButton);

    output.appendChild(column);
}

getClubs();

document.getElementById("club-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const data = {
        make: this.name.value,
    }

    axios.post("/clubs/create", data)
        .then(res => {
            getClubs();
            this.reset();
            this.make.focus();
        }).catch(err => console.log(err));

    console.log(this);
});

const deleteClub = async (id) => {
    const res = await axios.delete(`/clubs/delete/${id}`);
    getClubs();
};