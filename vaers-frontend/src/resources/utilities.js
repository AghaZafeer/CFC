export function convertSlugToUrl(slug, parameters) {
    let url = slug;
    Object.entries(parameters).forEach(([key, value]) => {
        url = url.replace(`:${key}`, value);
    });
    return url;
}
export function ordinal(number) {
    const english_ordinal_rules = new Intl.PluralRules("en", {
        type: "ordinal"
    });
    const suffixes = {
        one: "st",
        two: "nd",
        few: "rd",
        other: "th"
    }
    const suffix = suffixes[english_ordinal_rules.select(number)];
    return (number + suffix);
}
export function convertArraytoJsonObject(arrayData,parameter) {
    var jsonarray = [];
    if(arrayData !==null && arrayData !==undefined){
        arrayData.forEach(function(value) {
            var jsonObj = {};
            jsonObj[parameter] = value;
            jsonarray.push(jsonObj);
        });
    }
  return jsonarray;
}

export function eleContainsInArray(arr,element){
    if(arr != null && arr.length >0){
        for(var i=0;i<arr.length;i++){
            if(arr[i].adverseEffectID == element)
                return true;
        }
    }
    return false;
 }

export function checkDateValidation(firstDate,secondDate) {
    if(firstDate ===null ||  firstDate === undefined){
       return true;
    }
    if (firstDate === secondDate) {
        return true;
    }
    if ((new Date(firstDate) > new Date(secondDate)) || (new Date(secondDate) < new Date(firstDate))) {
      return false;
    } else {
      return true;
    }
  }

 export function groupBy(objectArray, property) {
    return objectArray.reduce((acc, obj) => {
       const key = obj[property];
       if (!acc[key]) {
          acc[key] = [];
       }
       acc[key].push(obj);
       return acc;
    }, {});
 }