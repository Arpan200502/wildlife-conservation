const w = "http://localhost:8081";
const o = "http://localhost:8082";
const c = "http://localhost:8083";

export const pages = [
  { title: "Animals", path: "/animals", url: `${w}/api/animals`, fields: [
    ["animalCode","Animal code"], ["speciesId","Species ID","number"], ["age","Age","number"], ["healthStatus","Health status"], ["location","Location"], ["protectedAreaId","Protected area ID","number"]
  ]},
  { title: "Species", path: "/species", url: `${w}/api/sp`, fields: [
    ["speciesName","Species name"], ["conservationStatus","Conservation status"], ["description","Description"]
  ]},
  { title: "Patrols", path: "/patrols", url: `${o}/api/patrols`, fields: [
    ["patrolCode","Patrol code"], ["staffId","Staff ID","number"], ["protectedAreaId","Protected area ID","number"], ["patrolDate","Patrol date","date"], ["status","Status"]
  ]},
  { title: "Incidents", path: "/incidents", url: `${o}/api/incidents`, fields: [
    ["incidentCode","Incident code"], ["incidentType","Incident type"], ["description","Description"], ["severity","Severity"], ["protectedAreaId","Protected area ID","number"], ["location","Location"], ["reportedDate","Reported date","date"], ["status","Status"]
  ]},
  { title: "Rescues", path: "/rescues", url: `${o}/api/rescues`, fields: [
    ["animalId","Animal ID","number"], ["rescueDate","Rescue date","date"], ["location","Location"], ["animalCondition","Animal condition"], ["rehabilitationStatus","Rehabilitation status"]
  ]},
  { title: "Staff", path: "/staff", url: `${o}/api/staff`, fields: [
    ["staffCode","Staff code"], ["staffName","Staff name"], ["role","Role"], ["phone","Phone"], ["assignedAreaId","Assigned area ID","number"], ["availabilityStatus","Availability status"]
  ]},
  { title: "Resources", path: "/resources", url: `${o}/api/resources`, fields: [
    ["resourceName","Resource name"], ["resourceType","Resource type"], ["quantity","Quantity","number"], ["condition","Condition"], ["protectedAreaId","Protected area ID","number"], ["availabilityStatus","Availability status"]
  ]},
  { title: "Protected Areas", path: "/protected-areas", url: `${c}/api/protected-areas`, fields: [
    ["name","Name"], ["location","Location"], ["areaSize","Area size","number"], ["description","Description"]
  ]},
  { title: "Zones", path: "/zones", url: `${c}/api/zones`, fields: [
    ["zoneName","Zone name"], ["protectedAreaId","Protected area ID","number"], ["description","Description"]
  ]},
  { title: "Habitats", path: "/habitats", url: `${c}/api/habitats`, fields: [
    ["habitatName","Habitat name"], ["zoneId","Zone ID","number"], ["condition","Condition"], ["description","Description"]
  ]},
  { title: "Conservation Programmes", path: "/programmes", url: `${c}/api/conservation-programs`, fields: [
    ["programName","Programme name"], ["protectedAreaId","Protected area ID","number"], ["description","Description"], ["status","Status"]
  ]}
];
export const conservationBase = c;
