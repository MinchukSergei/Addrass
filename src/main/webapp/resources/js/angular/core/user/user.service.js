'use strict';

angular
    .module('user')
    .factory('User', function ($http) {
        return {
            getFriends: function() {
                return $http.get('friend/')
                    .then(function (result) {
                        return result.data;
                    });
            },

            getUser: function(pkId) {
                return $http.get('user/' + pkId)
                    .then(function (result) {
                        return result.data;
                    });
            }
        }
    });
