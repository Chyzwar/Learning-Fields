/**
 *  Module
 *
 * Description
 */
angular.module('ProjectSeed')
    .controller('ListController', function ($scope, Contact, $location) {
        $scope.contacts = Contact.query();
        $scope.fields = ['firstName', 'lastName'];

        $scope.sort = function (field) {
            $scope.sort.field = field;
            $scope.sort.order = !$scope.order;
        };
        $scope.sort.field = 'firstName';
        $scope.sort.order = false;

        $scope.show = function (id) {
            $location.url('/contact/' + id);
        };

        console.log($scope.contacts);
    })
    .controller('NewController', function ($scope, Contact, $location) {
        $scope.contact = new Contact({
            firstname: ['', 'text'],
            lastName: ['', 'text'],
            email: ['', 'email'],
            homePhone: ['', 'tel'],
            cellPhone: ['', 'tel'],
            website: ['', 'url'],
            adress: ['', 'text']
        });

        $scope.save = function () {
            if ($scope.newContact.$invalid) {
                $scope.$broadcost('record:invalid');
            } else {
                $scope.Contact.$save();
                $location.url('/contacts');
            }
        };
    });
