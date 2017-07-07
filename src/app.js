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
  var customBlueMap = 		$mdThemingProvider.extendPalette('light-blue', {
    'contrastDefaultColor': 'light',
    'contrastDarkColors': ['50'],
    '50': 'ffffff'
  });
  $mdThemingProvider.definePalette('customBlue', customBlueMap);
  $mdThemingProvider.theme('default')
    .primaryPalette('customBlue', {
      'default': '500',
      'hue-1': '50'
    })
    .accentPalette('pink');
  $mdThemingProvider.theme('input', 'default')
        .primaryPalette('grey')
});
