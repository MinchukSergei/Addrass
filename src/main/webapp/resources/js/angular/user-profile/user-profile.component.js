'use strict';

angular.module('userProfile')
    .component('userProfile', {
        templateUrl: '/resources/js/angular/user-profile/user-profile.template.html',
        controller: ['User', '$scope',
            function UserProfileController(User, $scope) {
                this.assetPrefix = 'resources/images/assets/';

                this.photoPrefix = 'icon/';
                this.user = $scope.$parent.$parent.selectedUser;

                this.allUsers = function() {
                    $scope.$emit('allUsers');
                };
            }
        ]
    });
