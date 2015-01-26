var express = require('express'),
    bodyParser = require('body-parser');
app = express();

//This is middleware that will act between client and server 
app.use(bodyParser.urlencoded());

app.use(function(req, res, next) {
    console.log("custom middleware for each req");
    next();
});


//Build in midllware of Express
app.use(express.static('./public'));
app.get('/route', function(req, res) {
    res.render('route/index.jade', {
        names: names
    });
});

//Middleware based on named parameters in routes
app.param('name', function(req,res,next,name){
	req.name = name[0].toUpperCase() + name.substring(1);
	next();
});
app.get('/name/:name', function(req, res) {
    res.send('Your name is' + req.params.name);

});


//Setting confing in Express
//app.set();
//For bool options
//app.enabled();
//app.disabled();

//getters for options
//app.get();
//app.enabled();
//app.disabled();

app.set('env', 'development'); //process.env.Node_ENV(def undefined)
//Reverse proxy
app.enable('trust proxy');
app.set('jsonp callback name', 'cb');

//Define function that will used when calling JSON.stringify
app.set('json replacer', function(attr, val) {
    if (attr === 'passwordHash') {
        return undefined;
    } else {
        return val;
    }
});
//JSON.stringify({json: json},fn);
app.get('/user_info', function(req, res) {
    res.json(user); //JSON.stringify
});


app.enable('case sensitive routing'); // /hello /HELLO
app.enable('strict routing'); // /hello/ /hello
app.enable('view chache');

app.set('view engine', 'jade'); // There is no need to view extensions we can use index
app.set('views', 'views'); // Directory for views

app.disable('x-powered-by'); //Header name of server

//My Settings
app.set('mySetingGowno', 'Gownovalue');
app.get('mySetingGowno');

//all works for all get, post,put,delete
app.all('/', function(req, res, next) {
    console.log('From All');
    //next will move next
    next();
});

app.get('/', function(req, res) {
    res.render('index.jade', {
        title: "Hello Express"
    });
});

var names = [];

function log(req, res, next) {
        console.log(names + "Mistrzu Marcin");
        next();
    }
    //Nested callback as long as we use next()
app.get('/name', function(req, res, next) {
        console.log(names);
        next();
    },
    log,
    function(req, res) {
        res.render('names/index.jade', {
            names: names
        });
    });

app.post('/name', function(req, res) {
    names.push(req.body.name);
    res.redirect('/name');
});

app.listen(3000, function() {
    console.log("Listening on port 3000");
})
