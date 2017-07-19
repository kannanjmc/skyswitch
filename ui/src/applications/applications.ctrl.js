angular.module("SkyswitchApp")

.controller("ApplicationController", function ($scope, $mdDialog) {
  $scope.showAdd = function (ev) {
    $mdDialog.show({
        controller: "ApplicationAddController",
        templateUrl: 'applications/add/dialog-add.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose:true
      })
      .then(function(answer) {
        $scope.status = 'You said the information was "' + answer + '".';
      }, function() {
        $scope.status = 'You cancelled the dialog.';
      });
    };
});
