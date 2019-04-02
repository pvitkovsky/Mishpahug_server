const https = require('https');
const http = require('http');
var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
var xhrP = new XMLHttpRequest();
http.get('http://localhost:8080/administrator/listevent', (resp) => {
    let data = '';

    // A chunk of data has been recieved.
    resp.on('data', (chunk) => {
        data += chunk;
    });

    // The whole response has been received. Print out the result.
    resp.on('end', () => {
        let z = JSON.parse(data);
        console.log(z);
    });
});
http.get('http://localhost:8080/administrator/user', (resp) => {
    let data = '';

    // A chunk of data has been recieved.
    resp.on('data', (chunk) => {
        data += chunk;
    });

    // The whole response has been received. Print out the result.
    resp.on('end', () => {
        let z = JSON.parse(data);
        console.log(z);
    });
});
https.get('https://calendarific.com/api/v2/holidays?api_key=e3d29a00c8e79f50211cfd05e47f92c7c5735a5e&country=IL&year=2019', (resp) => {
    let data = '';

    // A chunk of data has been recieved.
    resp.on('data', (chunk) => {
        data += chunk;
    });

    // The whole response has been received. Print out the result.
    resp.on('end', () => {
        let z = JSON.parse(data);
        for (let i = 0; i < z.response.holidays.length; i++) {
            console.log(z.response.holidays[i]);
            let dataP = {};
            //dataP.assign();
            dataP.name = z.response.holidays[i].name;
            dataP.description = z.response.holidays[i].description;
            dataP.date = z.response.holidays[i].date.iso;
            dataP.locations = z.response.holidays[i].locations;
            dataP.states = z.response.holidays[i].states;
            dataP.type = z.response.holidays[i].type;

            xhrP.open("POST", 'http://localhost:8080/holidays', true);

//Send the proper header information along with the request
            xhrP.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhrP.onreadystatechange = function() { // Call a function when the state changes.
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                    // Request finished. Do processing here.
                }
            }
            xhrP.send(dataP);
        }

    });
});
