cordova.define('cordova/plugin/gps.detection', function(require, exports, module) {
	var exec = require('cordova/exec');

	var GPSDetection = function() {};

	GPSDetection.prototype.checkGPSService = function(gpsSuccessCallback, gpsErrorCallback, arguments) {
    	exec(gpsSuccessCallback, gpsErrorCallback, 'GPSDetection', 'GPS-SERVICE', arguments);
    };

	var GPSDetection = new GPSDetection();
	module.exports = GPSDetection;
});

