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
