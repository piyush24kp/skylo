module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json')
    });

    //copy
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.config('copy', {
        main: {
            files: [
                // makes all src relative to cwd
                {
                    expand: true,
                    cwd: 'app/',
                    src: ['**'],
                    dest: 'dest/'
                }
            ],
        }
    });

    //concat
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.config('concat', {

        options: {
            banner: "'use strict';\n",
            process: function(src, filepath) {
                return '// Source: ' + filepath + '\n' +
                    src.replace(/(^|\n)[ \t]*('use strict'|"use strict");?\s*/g, '$1');
            }
        },
        dist: {
            src: [
                'dest/app.mdl.js',
                'dest/app.route.js',
                'dest/*/**/*.mdl.js',
                'dest/*/config.**.js',
                'dest/**/**/*.js',
                'dest/*/*.cnst.js',
                'dest/*/**/*.ctrl.js',
                'dest/widgets/*/**/*.drv.js',
                'dest/widgets/*/**/*.fact.js',
                'dest/widgets/*/**/*.serv.js'
            ],
            dest: 'production/js/<%= pkg.name %>-<%= pkg.version %>.js',
        }

    });

    //jshint
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.config('jshint', {

        options: grunt.file.readJSON('jshintrc.json'),
        files: {
            src: ['app/*.js', 'app/**/*.js', 'app/**/**/*.js']
        }

    });

    //less
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.config('less', {
        development: {
            options: {
                paths: ['app/']
            },
            files: {
                "assets/css/gb.css": 'assets/css/main.less'
            }
        }
    });

    //uglify
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.config('uglify', {

        options: {
            banner: '/*! <%= pkg.name %>-<%= pkg.version %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
        },
        build: {
            src: 'production/js/<%= pkg.name %>-<%= pkg.version %>.js',
            dest: 'production/js/<%= pkg.name %>-<%= pkg.version %>.min.js'
        },

        watch: {
            scripts: {
                files: ['app/*.js', 'app/**/*.js', 'app/**/**/*.js'],
                tasks: ['jshint'],
                options: {
                    debounceDelay: 1000,
                }
            }
        }

    });

    //jsbeautifier
    grunt.loadNpmTasks('grunt-jsbeautifier');
    grunt.config('jsbeautifier', {

        files: ['Gruntfile.js', 'app/**/*.js'],
        options: {}

    });

    //cssmin
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.config('cssmin', {

        target: {
            files: {
                'production/css/<%= pkg.name %>-<%= pkg.version %>.min.css': ['assets/css/*.css', 'assets/**/!*.min.css']
            }
        }

    });

    //clean
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.config('clean', {

        folder: ['dest'],
        css: ['production/css/*.css'],
        js: ['production/js/*.js']

    });

    //csslint
    grunt.loadNpmTasks('grunt-contrib-csslint');
    grunt.config('csslint', {

        strict: {
            options: {
                import: 2
            },
            src: ['assets/css/*.css', 'assets/**/!*.min.css']
        }

    });

    //, 'cssmin', 'csslint'
    grunt.registerTask('default', ['jshint', 'clean', 'copy', 'concat', 'uglify', 'jsbeautifier']);

    //, 'cssmin', 'csslint'
    grunt.registerTask('build', ['jshint', 'clean', 'less', 'copy', 'concat', 'uglify', 'jsbeautifier']);
}
