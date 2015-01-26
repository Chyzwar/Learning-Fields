var express = require('express'),
    bodyParser = require('body-parser'),
    app = express(),
    APIv1 = require('./APIv1'),
    APIv2 = require('./APIv2');

//Injecting Rourter Objects on given path
app.use('/api/v1',APIv1);
app.use('/api/v2',APIv2);

app.use(bodyParser.urlencoded());



var names = ['Marian'];

//Route object that can have all these methods
// app.route('/path')
//     .get()
//     .post()
//     .put()
//     .delete()
//     .all();

app.route('/')
    .get(function(req, res, next) {
        console.log(names);
        res.render('names/index.jade', {
            names: names
        });
        next();
    })
    .post(function(req, res) {
        names.push(req.body.name);
        res.redirect('/');
    });


//Router Object mini express app inside master app
// -use for middleware
// -verb /all (put,get .. )
// -param for route specific middleware
//- route method (route object)


var router = express.Router({
    caseSensitive: false, //application wioe option that can be used
    strict: true,
});

router.use(function(req, res, next) {
    console.log("Rputer specific middleware");
    next();
});
router.get('/', function(req, res) {
    res.send('Router home root');
});

router.route('/test')
    .get(function(req, res) {
        res.send('Router route');
    });


app.use('/router', router); //Whe put route path were router object will be used


//Modularity in two files

app.listen(3000);
