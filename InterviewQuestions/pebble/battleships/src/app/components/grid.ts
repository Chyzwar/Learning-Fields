/// <reference path="../../typings/_custom.d.ts" />

import {Component, CORE_DIRECTIVES, EventEmitter} from 'angular2/angular2';

/*
 * Directive Grid
 */
@Component({
	selector: 'grid',
	inputs: ['grid'],
	outputs: ['select'],
	directives: [
		CORE_DIRECTIVES
	],
	styles: [
		require('./grid.css') // webpack require
	],
	template: `
	<div class="grid">
      <div *ng-for="#row of grid; #x=index" class="row">
        <div *ng-for="#tile of row; #y=index">
          <div class="tile"
               [class.x]="tile.isHit==true"
               [class.o]="tile.isHit==false"
               (click)="select.next({x: x, y: y})">
          </div>
        </div>
      </div>
    </div>
	`
})

export class Grid {
	select: EventEmitter = new EventEmitter();

	constructor() {

	}

	onChange() {
		console.log('change')
	}
}