'use strict';

angular.module('userList')
    .component('userList', {
        templateUrl: 'resources/js/angular/user-list/user-list.template.html',
        controller: ['User', '$scope',
            function UserListController(User, $scope) {
                this.assetPrefix = 'resources/images/assets/';
                this.photoPrefix = 'icon/';

                var parent = this.parent;

                var self = this;

                User.getFriends().then(function(data) {
                    self.users = data;
                });


            }
        ]
        //scope : {
        //    selected : '='
        //}
    });