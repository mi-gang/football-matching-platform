
$(document).ready(function () {
    fetch('/schedule/matches/count', {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            $('#totalNum').text(data);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });

    fetch('/schedule/matches', {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (Array.isArray(data)) {
                data.forEach(item => {
                    console.log(item);
                    getMatchigList(item);
                });
            } else {
                console.error('Expected an array but got:', data);
            }
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
});
