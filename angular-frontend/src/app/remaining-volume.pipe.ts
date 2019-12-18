import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'remainingVolume'
})
export class RemainingVolumePipe implements PipeTransform {

  transform(value: string): string {
    if (value == null)
      return ""
    else
      return "Remaining:" + value + "L";
  }

}
