import axios from "axios";

export function getVaccines1() {
   const r = [{"vaccineID":1,"vaccineName":"Covaxin","vaccineManufacturer":"Serum","vaccineIsActive":true,
   "vaccineDoses":[{"vaccineDoseId":1,"vaccineDoseName":"Dose 1","vaccineDoseDetail":null},
   {"vaccineDoseId":2,"vaccineDoseName":"Dose 2","vaccineDoseDetail":null}]},
   {"vaccineID":2,"vaccineName":"Covisheild","vaccineManufacturer":"Biotek","vaccineIsActive":true,
   "vaccineDoses":[{"vaccineDoseId":3,"vaccineDoseName":"Dose 1","vaccineDoseDetail":null},
   {"vaccineDoseId":4,"vaccineDoseName":"Dose 2","vaccineDoseDetail":null}]},
   {"vaccineID":3,"vaccineName":"Sputnik","vaccineManufacturer":"Russia","vaccineIsActive":true,
   "vaccineDoses":[{"vaccineDoseId":5,"vaccineDoseName":"Dose 1","vaccineDoseDetail":null}]},
   {"vaccineID":4,"vaccineName":"Astrazeneca","vaccineManufacturer":"Europe","vaccineIsActive":true,
   "vaccineDoses":[{"vaccineDoseId":6,"vaccineDoseName":"Dose 1","vaccineDoseDetail":null},
   {"vaccineDoseId":7,"vaccineDoseName":"Dose 2","vaccineDoseDetail":null}]},
   {"vaccineID":5,"vaccineName":"Moderna","vaccineManufacturer":"USA","vaccineIsActive":true,
   "vaccineDoses":[{"vaccineDoseId":8,"vaccineDoseName":"Dose 1","vaccineDoseDetail":null},
   {"vaccineDoseId":9,"vaccineDoseName":"Dose 2","vaccineDoseDetail":null}]},
   {"vaccineID":6,"vaccineName":"J&J",
    "vaccineManufacturer":"Europe","vaccineIsActive":true,"vaccineDoses":[{"vaccineDoseId":10,"vaccineDoseName":"Dose 1","vaccineDoseDetail":null}]}];
    console.log(r);
    return r;
  }

export function getVaccines() {
  axios.get('/getVaccineList').then(res => {
    console.log(res.data);
    return res.data; 
    }).catch(err => console.log(err))
  };

  export const getSymptoms1 = async () => {
    const response = await fetch('/getParentAdverseEvents');
    const body = await response.json();
    console.log("JSON RESPONSE" + body);
    if (response.status !== 200) throw Error(body.message);
    return body;
  };

  export function exportGraphCriteria() {
    return [
      {name: 'C1', value: 'By Doses'},
      {name: 'C2', value: 'By Severity'},
      {name: 'C3', value: 'By Ages'},
       ]
  }

  export function getNoofRecords() { 
    return {name : "totaluser" , value :'500', 
    listofvaccines : [{"vaccineID":1,"vaccineName":"Covaxin", totaluser : '70',
    listofadverseevent : [{"vaccineDoseId":1,"vaccineDoseName":"DOSE 1", totaluser : '35',
    symptoms : [{name:"adverseevent1", value :"Fever", totaluser :20 , milduser:7 , moderateuser:7 , severeuser:6 , agegroup1: 7, agegroup2:7 , agegroup3:6},
                {name:"adverseevent2", value :"Tiredness", totaluser :10 , milduser:2 , moderateuser:6 , severeuser:2,agegroup1: 5, agegroup2:2 , agegroup3:3 },
                {name:"adverseevent3", value :"Diarrhea", totaluser :5, milduser:1 , moderateuser:2 , severeuser:2,agegroup1: 1, agegroup2:2 , agegroup3:2  },
                {name:"adverseevent4", value :"Loose Motion", totaluser :10, milduser:2 , moderateuser:3 , severeuser:5,agegroup1: 5, agegroup2:2 , agegroup3:3   },
                {name:"adverseevent5", value :"headache", totaluser :10, milduser:2 , moderateuser:3 , severeuser:5, agegroup1: 5, agegroup2:2 , agegroup3:3 }
               ]
              },
              {"vaccineDoseId":2,"vaccineDoseName":"DOSE 2",totaluser :'44',
    symptoms : [{name:"adverseevent1", value :"Fever", totaluser :20, milduser:3 , moderateuser:12 , severeuser:5, agegroup1: 5, agegroup2:2 , agegroup3:3 },
                {name:"adverseevent2", value :"Tiredness", totaluser :20, milduser:10 , moderateuser:5 , severeuser:5, agegroup1: 10, agegroup2:5 , agegroup3:5},
                {name:"adverseevent3", value :"Diarrhea", totaluser :4 ,milduser:1 , moderateuser:2 , severeuser:1,agegroup1: 1, agegroup2:2 , agegroup3:1  }           
                ]
                        },        
            ]},
                {"vaccineID":2, "vaccineName":"CoviSheild",totaluser : '150', 
    listofadverseevent : [{"vaccineDoseId":3,"vaccineDoseName":"DOSE 1",totaluser : '75',
    symptoms : [{name:"adverseevent1", value :"Fever", totaluser :65,  milduser:40 , moderateuser:20 , severeuser:5 , agegroup1: 40, agegroup2:20 , agegroup3:5},
                {name:"adverseevent2", value :"Tiredness", totaluser :23, milduser:13 , moderateuser:7 , severeuser:3, agegroup1: 14, agegroup2:8 , agegroup3:1 },
                {name:"adverseevent3", value :"Diarrhea", totaluser :12, milduser:6 , moderateuser:4 , severeuser:2, agegroup1: 5, agegroup2:5, agegroup3:2}]
              },
              {"vaccineDoseId":4,"vaccineDoseName":"DOSE 2", totaluser :'74',
    symptoms : [{name:"adverseevent1", value :"Fever", totaluser :5, milduser:1 , moderateuser:1 , severeuser:3, agegroup1: 1, agegroup2:2 , agegroup3:2},
                {name:"adverseevent2", value :"Tiredness", totaluser :20,milduser:10 , moderateuser:5 , severeuser:5,agegroup1: 10, agegroup2:5 , agegroup3:5  },
                {name:"adverseevent3", value :"Diarrhea", totaluser :49,milduser:20 , moderateuser:9 , severeuser:20,agegroup1: 20, agegroup2:20 , agegroup3:9  }]
              }       
            ]},
                {"vaccineID":6, "vaccineName":"J&J",totaluser : '150', 
    listofadverseevent : [{"vaccineDoseId":10,"vaccineDoseName":"DOSE 1", totaluser :'75',
    symptoms : [{name:"adverseevent1", value :"Fever", totaluser :5},
                {name:"adverseevent2", value :"Tiredness", totaluser :28  },
                {name:"adverseevent3", value :"Diarrhea", totaluser :12  }]
              }]
                }
                ]
              
              };
  }

export function getSeverity() {
  return [{"SevId" : "1", 'SevName' : "Mild"},
  {"SevId" : "2", 'SevName' : "Moderate"},
  {"SevId" : "3", 'SevName' : "Severe"},
  ];
}

  export function getSymptoms() {
    return [
  {name: 'Symtoms1', value: 'Fever'},
  {name: 'Symtoms2', value: 'tiredness'},
  {name: 'Symtoms3', value: 'dry cough'},
  {name: 'Symtoms4', value: 'difficulty breathing or shortness of breath'},
  {name: 'Symtoms5', value: 'loss of speech or movement'},
  {name: 'Symtoms6', value: 'headache'},
  {name: 'Symtoms7', value: 'diarrhoea'},
  {name: 'Symtoms8', value: 'Others'}
   ]
  }

  export function getTemparatures() {
    return [
  {name: 'T1', value: '96°F to 99°F'},
  {name: 'T2', value: '100°F to 102°F'},
  {name: 'T3', value: '102°F to 104°F'},
   ]
  }
 
 