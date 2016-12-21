'use strict';

angular.module('addrassApp')
    .filter('hexify', function() {
        return function (x) {
            var res = '#';
            var hex = ('000000' + x.toString(16)).substr(-6);
            return res + hex;
        }
    });