var APIv2 = require('express').Router();


APIv2.get('/', function(req, res) {
    res.send('Name API version 2');
});

module.exports = APIv2;