"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var eventlist_service_1 = require("./eventlist.service");
var EventlistComponent = /** @class */ (function () {
    function EventlistComponent(EventServeice) {
        this.EventServeice = EventServeice;
        this.output = new core_1.EventEmitter();
        this.events = [];
        this.keys = [];
    }
    EventlistComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.EventServeice.getEvents().subscribe(function (response) {
            //console.log(response);
            _this.keys = Object.keys(response[0]);
            console.log(_this.keys);
            for (var event_1 in response) {
                //console.log(response[event]);
                for (var key in _this.keys) {
                    //console.log(this.keys[key]);
                    //console.log(response[event][this.keys[key]]);
                }
                console.log(response[event_1]);
                console.log(response[event_1][_this.keys[7]][0]);
                _this.events.push(response[event_1]);
            }
        });
    };
    __decorate([
        core_1.Input(),
        __metadata("design:type", Object)
    ], EventlistComponent.prototype, "array", void 0);
    __decorate([
        core_1.Output(),
        __metadata("design:type", Object)
    ], EventlistComponent.prototype, "output", void 0);
    EventlistComponent = __decorate([
        core_1.Component({
            selector: 'app-eventlist',
            templateUrl: './eventlist.component.html',
            styleUrls: ['./eventlist.component.scss',
            ]
        }),
        __metadata("design:paramtypes", [eventlist_service_1.EventlistService])
    ], EventlistComponent);
    return EventlistComponent;
}());
exports.EventlistComponent = EventlistComponent;
//# sourceMappingURL=eventlist.component.js.map