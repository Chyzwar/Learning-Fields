'use strict';

/**
 * @ngdoc function
 * @name angularTestfieldApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the angularTestfieldApp
 */
angular.module('angularTestfieldApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
