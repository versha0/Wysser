import {Deliverystop} from './deliverystop';

export interface Deliveryroute {
  vehicleId: string,
  orderVolume: number,
  deliveryStops: Deliverystop[]
}
