/*
 * Angular 2 decorators and services
 */
import {Component, View} from 'angular2/angular2';
import {CORE_DIRECTIVES} from 'angular2/angular2';

import {Grid} from './components/grid';
import {GameService} from './services/GameService'


/*
 * App Component
 * Top Level Component
 */
@Component({
  selector: '#app',
  bindings: [
    GameService
  ],
  directives: [
    CORE_DIRECTIVES,
    Grid
  ],
  styles: [
    require('./main.css')
  ],
  templateUrl: 'views/main.html'
})

export class App{
  title: string;

  constructor(public game: GameService) {
    this.title = 'Battleships Mega Game';
  }

  reset() {
    this.game = GameService.create();
  }
}
