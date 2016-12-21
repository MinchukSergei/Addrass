'use strict';

angular.module('userProfile')
    .component('userProfile', {
        templateUrl: '/resources/js/angular/user-profile/user-profile.template.html',
        controller: ['User',
            function UserProfileController(User) {
                var self = this;

                self.user = User.getUser();
            }
        ]
    });
