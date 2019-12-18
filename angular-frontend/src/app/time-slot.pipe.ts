import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'timeSlot'
})
export class TimeSlotPipe implements PipeTransform {

  transform(value: string): string {
    if (value == "slot1") {
      return "11:00-14:00";
    } else if (value == "slot2") {
      return "15:00-18:00";
    } else {
      return "19:00-22:00";
    }
  }

}
