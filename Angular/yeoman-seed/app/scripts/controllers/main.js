'use strict';

/**
 * @ngdoc function
 * @name angularTestfieldApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the angularTestfieldApp
 */
angular.module('angularTestfieldApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
