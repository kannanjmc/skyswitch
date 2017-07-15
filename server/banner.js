var fs = require("fs");

function printBanner(lineOutputFn) {
	if(!lineOutputFn) {
		// eslint-disable-next-line no-console
		lineOutputFn = console.log;
	}

	var lines = fs.readFileSync("banner.txt", "utf8").toString().split("\n");
	for(i in lines) {
		lineOutputFn(lines[i]);
	}
}

module.exports = {
	printBanner: printBanner
};
