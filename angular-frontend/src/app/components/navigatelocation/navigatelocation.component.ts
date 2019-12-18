import {Component, NgZone, OnInit} from '@angular/core';
import * as map from 'mapbox-gl';
import * as MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import * as forwardGeocoder from '@mapbox/mapbox-gl-geocoder';
import * as mapboxgl from 'mapbox-gl';
import {RoutingapiserviceService} from '../../services/routingapiservice.service';
import {InteractionService} from '../../services/interaction.service';

@Component({
  selector: 'app-navigatelocation',
  templateUrl: './navigatelocation.component.html',
  styleUrls: ['./navigatelocation.component.css']
})
export class NavigatelocationComponent implements OnInit {
  addresses = [];
  jsonresponse;
  allcoordinates;
  points = [];
  coordinates = [];
  waypoints: any;
  constructor(private routeapiservice: RoutingapiserviceService, private zone: NgZone,
              private interactionservice: InteractionService) { }

  ngOnInit() {
    // map.accessToken = 'pk.eyJ1IjoiZ2F1dGhhbTk5IiwiYSI6ImNrMzRlMmxrNjE0ZTMzbXBhOWRwdDk1eTcifQ.-ZceQ8jARpf90y0tJnQhoQ';
    // (map as any).accessToken = 'pk.eyJ1IjoiZ2F1dGhhbTk5IiwiYSI6ImNrMzRlMmxrNjE0ZTMzbXBhOWRwdDk1eTcifQ.-ZceQ8jARpf90y0tJnQhoQ';
    this.interactionservice.messageSource.subscribe(
      message => {
        console.log(message);
        this.addresses[0] = message.split(' ')[0];
        this.addresses[1] = message.split(' ')[1];
        console.log(this.addresses);
        // console.log((this.addresses[0]));


      }
    );
    this.maputil(this.addresses);
  }
  maputil(address) {
    this.routeapiservice.getLatandLong(address).subscribe((data) => {
      this.zone.run(() => {
        this.allcoordinates = data;
        this.coordinates = this.updatevalue(data);
        updatepointsonmap(this.coordinates, address);
        console.log(this.coordinates);
        this.routeapiservice.getGeoJsonLatLOng(this.coordinates, address).subscribe((data2) => {
          this.zone.run(() => {
            this.waypoints = data2;
            makegeojsonline(data2, this.addresses.length - 1);
            // temp2.resourceSets[0].resources[0].routePath.line.coordinates
            // console.log(data2)
          });
        });


        // console.log(data)
      });

    });


    //     for(i=0;i<this.addresses.length;i++)
    //     {
    //   var lat = this.allcoordinates[i].resourceSets.resources[0].point.coordinates[1]
    //   var long = this.allcoordinates[i].resourceSets.resources[0].point.coordinates[0]
    //             // console.log(this.jsonresponse[0].resources[0].point.coordinates
    //   var geomarker = new mapboxgl.Marker({
    //     draggable: true,
    //   })

    //     .setLngLat([lat, long])
    //     .addTo(mapp);
    // }

    // console.log(this.allcoordinates)
    const coordinates = document.getElementById('coordinates');
    Object.getOwnPropertyDescriptor(map, 'accessToken').set('pk.eyJ1IjoiZ2F1dGhhbTk5IiwiYSI6ImNrMzRlMmxrNjE0ZTMzbXBhOWRwdDk1eTcifQ.-ZceQ8jARpf90y0tJnQhoQ');
    const mapp = new map.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [77.65542724609375, 12.940049667358398],
        zoom: 9 // starting zoom

      }
    );


    const geomarker = new mapboxgl.Marker({
      draggable: true,
    }).setLngLat([-74.50, 40])
      .addTo(mapp);

    const geocoder = new MapboxGeocoder({
      accessToken: map.accessToken,
      localGeocoder: forwardGeocoder,
      zoom: 14,
      placeholder: 'Enter search e.g. Lincoln Park',
      mapboxgl,
      marker: false,
    });

    console.log(geocoder);
    mapp.addControl(geocoder);

    function onDragEnd() {
      const lngLat = geomarker.getLngLat();
      coordinates.style.display = 'block';
      coordinates.innerHTML = 'Longitude: ' + lngLat.lng + '<br />Latitude: ' + lngLat.lat;
      // console.log('Longitude: ' + lngLat.lng + '<br />Latitude: ' + lngLat.lat)
    }
    mapp.addControl(new mapboxgl.GeolocateControl({
      positionOptions: {
        enableHighAccuracy: true
      },
      trackUserLocation: true
    }));
    // function result()
    //   {
    //     var lngLat = MapboxGeocoder.getLngLat();
    //     coordinates.style.display = 'block';
    //     coordinates.innerHTML = 'Longitude: ' + lngLat.lng + '<br />Latitude: ' + lngLat.lat;
    //     console.log('Longitude: ' + lngLat.lng + '<br />Latitude: ' + lngLat.lat)
    //   }
    geomarker.on('dragend', onDragEnd);
    geocoder.on('results', function(result) {
      console.log(result.features[0].center);
      coordinates.style.display = 'block';
      const longitude = result.features[0].center[0];
      const latitude = result.features[0].center[1];
      coordinates.innerHTML = 'Longitude: ' + longitude + '<br />Latitude: ' + latitude;
      geomarker.setLngLat([longitude, latitude]);
    });
	    mapp.addControl(new mapboxgl.GeolocateControl({
      positionOptions: {
      enableHighAccuracy: true
      },
      trackUserLocation: true
      }));  

    function makegeojsonline(coordinateset, lengthofset) {
      mapp.on('load', function() {
        for (let i = 0; i < lengthofset; i++) {
          // console.log(coordinateset[i].resourceSets[0].resources[0].routePath.line.coordinates)
          const actualcordinates = coordinateset[i].resourceSets[0].resources[0].routePath.line.coordinates;
          const revcoordinates = [];
          // console.log(actualcordinates)
          for (let j = 0; j < actualcordinates.length; j++) {
            revcoordinates[j] = [actualcordinates[j][1], actualcordinates[j][0]];
          }
          console.log(revcoordinates);
          mapp.addLayer({
            id: 'route' + i,
            type: 'line',
            source: {
              type: 'geojson',
              data: {
                type: 'Feature',
                properties: {},
                geometry: {
                  type: 'LineString',
                  coordinates: revcoordinates
                }
              }
            },
            layout: {
              'line-join': 'round',
              'line-cap': 'round'
            },
            paint: {
              'line-color': '#ff0808',
              'line-width': 5
            }
          });
        }

      });


    }

    function updatepointsonmap(coordinates, addresses) {
      for (let i = 0; i < addresses.length; i++) {
        const geomarker = new mapboxgl.Marker({
          draggable: true,
        }).setLngLat([coordinates[i][1], coordinates[i][0]])
          .addTo(mapp);
      }
    }
  }
  updatevalue(data) {
    this.allcoordinates = data;
    for (let i = 0; i < this.addresses.length; i++) {
      this.coordinates[i] = this.allcoordinates[i].resourceSets[0].resources[0].point.coordinates;
    }
    // console.log(this.coordinates)
    return this.coordinates;
  }

  onsavebutton() {
    console.log('hi from the save button');
  }
}
