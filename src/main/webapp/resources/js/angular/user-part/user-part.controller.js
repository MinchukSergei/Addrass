'use strict';

angular.module('userPart')
    .controller('userPart',
        ['$scope', function ($scope) {
            var types = ['friends', 'contacts', 'profile'];
            $scope.selectionType = types;
            $scope.selected = types[0];

            $scope.selectedUser = null;

            $scope.$on('changeUser', function(event, data) {
                $scope.selected = types[2];
                $scope.selectedUser = data;
            });

            $scope.$on('allUsers', function(event, data) {
                $scope.selected = types[0];
            });
        }
        ]);
