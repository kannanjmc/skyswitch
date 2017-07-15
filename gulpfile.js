var gulp = require("gulp");
var concat  = require("gulp-concat");
var sourcemaps = require("gulp-sourcemaps");
var ngAnnotate = require("gulp-ng-annotate");
var pump = require('pump');
var uglify = require('gulp-uglify');
var sass = require('gulp-sass');

var target = "dist";

var sources = {
	"js": ["src/app.js", "src/**/*.js"],
	"static": ["src/**/*.html"],
	"sass": ["src/**/*.scss", "!**/_*.scss"]
};

gulp.task("static", function () {
	return gulp.src(sources.static)
		.pipe(gulp.dest(target));
});

gulp.task('sass', function () {
  return gulp.src(sources.sass)
		.pipe(sourcemaps.init())
    .pipe(sass({outputStyle: 'compressed'}).on('error', sass.logError))
		.pipe(concat("app.min.css"))
		.pipe(sourcemaps.write("maps"))
		.pipe(gulp.dest(target + "/css"));
});

gulp.task("js", function (cb) {
  pump([
        gulp.src(sources.js),
				sourcemaps.init(),
        ngAnnotate(),
        uglify({
					"output": {
						"comments": "/^!/"
					}
				}),
				concat("skyswitch.min.js"),
				sourcemaps.write("maps"),
				gulp.dest(target)
    ],
    cb
  );
});

gulp.task("default", ["js", "static", "sass"]);

gulp.task("watch", ["default"], function () {
	gulp.watch(sources.js, ["js"]);
	gulp.watch(sources.static, ["static"]);
	gulp.watch(sources.sass, ["sass"]);
});
