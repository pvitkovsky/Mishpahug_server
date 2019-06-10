import {Component, OnInit} from '@angular/core';
import {ChoicesConnection} from '../../Models';
import {Choice} from '../../Models/Choices';
import {ChoicesService} from '../../Services/choices.service';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.scss']
})
export class EventDetailsComponent implements OnInit {

  choices : Choice[];

  constructor(private choicesService : ChoicesService) {
    this.choices = [];


  }

  ngOnInit() {
    for (let choiceName in ChoicesConnection){
      console.log('wtf choice name ' + choiceName);
      let typedChoice : keyof typeof ChoicesConnection = choiceName as keyof typeof ChoicesConnection; // getting enum out of string;
      let choiceVariants : string[];
      this.choicesService.getOptions(ChoicesConnection[typedChoice]).subscribe(res => {
        choiceVariants = res
        console.log('received choice variants ' + choiceVariants);
        this.choices.push(new Choice(choiceName, choiceVariants));
      });
    }
    //TODO: after subscription ends;
    for(let choice of this.choices){
      console.log(choice.name + ' ' + choice.variants );
    }
  }

}
