/// <reference path="../typings/_custom.d.ts" />

/*
 * Providers provided by Angular
 */
import {bootstrap} from 'angular2/angular2';


/*
 * App Component
 * our top level component that holds all of our components
 */
import {App} from './app';

/*
 * Bootstrap our Angular app with a top level component `App` and inject
 * our Services and Providers into Angular's dependency injection
 */
bootstrap(App, [
  // These are dependencies of our App
]);
