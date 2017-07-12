var yaml = require("node-yaml");
var path = require("path");

var logFactory = require("./backend/logfactory");
var banner = require("./backend/banner");

var express = require("express");
var app = express();

var config = yaml.readSync(path.join(__dirname, "config.yml"));

// configure logger
logFactory.level("debug");
var log = logFactory.createLogger();

// output start banner
banner.printBanner(log.info);

app.use("/vendor", express.static(path.join(__dirname, "node_modules/angular")));
app.use("/vendor", express.static(path.join(__dirname, "node_modules/angular-animate")));
app.use("/vendor", express.static(path.join(__dirname, "node_modules/angular-aria")));
app.use("/vendor", express.static(path.join(__dirname, "node_modules/angular-material")));
app.use("/vendor", express.static(path.join(__dirname, "node_modules/angular-resource")));
app.use("/vendor", express.static(path.join(__dirname, "node_modules/angular-route")));
app.use("/vendor", express.static(path.join(__dirname, "node_modules/angular-messages")));
app.use("/vendor", express.static(path.join(__dirname, "node_modules/jquery/dist")));

app.use("/", express.static(path.join(__dirname, "node_modules/material-design-icons/iconfont")));
app.use("/css", express.static(path.join(__dirname, "node_modules/font-awesome/css")));
app.use("/fonts", express.static(path.join(__dirname, "node_modules/font-awesome/fonts")));

app.use("/", express.static(path.join(__dirname, "dist")));

app.listen(config.server.port, function () {
	log.info("Skyswitch started and listens on port %s", config.server.port);
});
