/*!
         __                       _ __       __
   _____/ /____  ________      __(_) /______/ /_
  / ___/ //_/ / / / ___/ | /| / / / __/ ___/ __ \
 (__  ) ,< / /_/ (__  )| |/ |/ / / /_/ /__/ / / /
/____/_/|_|\__, /____/ |__/|__/_/\__/\___/_/ /_/
          /____/

*/

angular.module("SkyswitchApp", ["ngResource", "ngRoute", "ngMaterial"])

.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('input', 'default')
        .primaryPalette('light-blue');
});
