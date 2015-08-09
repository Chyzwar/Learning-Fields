var http = require('http');

var server = http.createServer(function (request, reposnse){
		response.writeHead(200, {"Content-Type" : "text/plain"});
		response.end("Hello Worls");
});


server.listen(port, [hostname], [backlog], [callback])