var jq = jQuery.noConflict();

/**
 * Project Seed Module
 *
 * Description
 */
angular.module('ProjectSeed', ['ngRoute', 'ngResource'])
    .config(function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/contact', {
                controller: 'ListController',
                templateUrl: 'views/list.html'
            })
            .when('/contact/new', {
                controller: 'NewController',
                templateUrl: 'views/new.html'
            });
        $locationProvider.html5Mode(true);
    });
