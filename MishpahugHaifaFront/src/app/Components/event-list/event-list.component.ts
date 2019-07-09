import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { ActivatedRoute, Router, } from '@angular/router';
import { UserService  } from  '../../Services/index';
import { UserDetail } from  '../../Models/index';
import { filter } from 'rxjs/operators'

export enum RenderEvents {
	ALL = 'ALL',
	OWNER = 'OWNER',
	GUEST = 'GUEST'
}

@Component({
	selector: 'app-eventlist',
	templateUrl: './event-list.component.html',
	styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit{

	childEventList : RenderEvents = RenderEvents.ALL;
	loggedInUserId : number;

	constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {}

	ngOnInit(){
		this.route.queryParams.subscribe(params => { 	//TODO : navigation onto secured path when rendering Owner and GUEST	
			let render = params['render'];
			if(render){ 
				this.childEventList = render;
			}
		});
	}
}

