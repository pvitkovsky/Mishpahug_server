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
var http_1 = require("@angular/common/http");
var operators_1 = require("rxjs/operators");
var EventlistService = /** @class */ (function () {
    function EventlistService(http) {
        this.http = http;
    }
    EventlistService.prototype.getEvents = function () {
        var headers = new http_1.HttpHeaders({ 'Authorization': 'not null Authorization' });
        return this.http.get("/event/", { headers: headers }).pipe(operators_1.map(function (res) {
            console.log(res);
            return res;
        }));
    };
    EventlistService = __decorate([
        core_1.Injectable(),
        __metadata("design:paramtypes", [http_1.HttpClient])
    ], EventlistService);
    return EventlistService;
}());
exports.EventlistService = EventlistService;
//# sourceMappingURL=eventlist.service.js.map