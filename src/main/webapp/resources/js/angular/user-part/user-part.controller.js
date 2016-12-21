'use strict';

angular.module('userPart')
    .controller('userPart',
        ['$scope', function ($scope) {
            var types = ['friends', 'contacts', 'profile'];
            $scope.selectionType = types;
            $scope.selected = types[0];

            $scope.user = {
                id: 2
            };
        }
        ]);
