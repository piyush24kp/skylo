// Karma configuration
// Generated on Mon May 16 2016 15:48:01 GMT+0530 (India Standard Time)

module.exports = function(config) {
    config.set({

        // base path that will be used to resolve all patterns (eg. files, exclude)
        basePath: '',


        // frameworks to use
        // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
        frameworks: ['jasmine'],


        // list of files / patterns to load in the browser
        files: [
            'bower_components/jquery/dist/jquery.js',
            'bower_components/angular/angular.js',
            'bower_components/angular-route/angular-route.js',
            'bower_components/bootstrap/dist/js/bootstrap.js',
            'bower_components/angular-animate/angular-animate.js',
            'bower_components/angular-sanitize/angular-sanitize.js',
            'bower_components/toastr/toastr.js',
            'bower_components/moment/moment.js',
            'bower_components/extras.angular.plus/ngplus-overlay.js',
            'bower_components/angular-mocks/angular-mocks.js',
            'http://cdn.gigya.com/JS/gigya.js?apiKey=3_mocfVbaYhKWGy9A-kPHm2UhrxbyfqG7nv1DCLAX1Mj8J5vlVod9sxZI7eLx4ywRC',
            'dest/app.mdl.js',
            'dest/app.route.js',
            'dest/*/**/*.mdl.js',
            'dest/*/config.**.js',
            'dest/**/**/*.js',
            'dest/*/*.cnst.js',
            'dest/*/**/*.ctrl.js',

            'test/**/*.spec.js'

        ],




        // preprocess matching files before serving them to the browser
        // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
        preprocessors: {},


        // test results reporter to use
        // possible values: 'dots', 'progress'
        // available reporters: https://npmjs.org/browse/keyword/karma-reporter
        reporters: ['progress'],


        // web server port
        port: 9876,

        junitReporter: {
            outputFile: 'test_out/unit.xml',
            suite: 'unit'
        },
        // enable / disable colors in the output (reporters and logs)
        colors: true,


        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,


        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,


        // start these browsers
        // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
        browsers: ['PhantomJS'],


        // Continuous Integration mode
        // if true, Karma captures browsers, runs the tests and exits
        singleRun: false,

        // Concurrency level
        // how many browser should be started simultaneous
        concurrency: Infinity
    })
}
