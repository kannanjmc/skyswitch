const winston = require("winston");
const dateFormat = require("dateformat");

function createLogger() {
	return new (winston.Logger)({
		transports: [
			new (winston.transports.Console)({
				timestamp: function() {
					return dateFormat(Date.now(), "yyyy-mm-dd HH:MM:ss");
				},
				formatter: function(options) {
					return options.timestamp() +" "+ options.level.toUpperCase() +"  "+ (options.message ? options.message : "") +
						(options.meta && Object.keys(options.meta).length ? "\n\t"+ JSON.stringify(options.meta) : "" );
				}
			})
		]
	});
}

function level(loglevel) {
	winston.level = loglevel;
}

module.exports = {
	createLogger: createLogger,
	level: level
};
