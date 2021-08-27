const color = {
		brightBlue: '#07A9E0',
	    darkGrayishBlue: '#8b8d94',
	    darkRed: '#a90000',
	    grayishBlue: '#A4A6B3',
	    grayishBlue2: '#9fa2b4',
	    grayishBlue3: '#bdc3c7',
	    lightBlue: '#07A9E0',
	    lightGrayishBlue: '#F7F8FC', // background color
	    lightGrayishBlue2: '#DFE0EB',
	    paleBlue: 'rgb(6, 212, 242)',
	    paleBlueTransparent: 'rgba(6, 212, 242, 0.5)',
	    veryDarkGrayishBlue: '#041D46',
	    white: '#FFFFFF'
};

const typography = {
    cardTitle: {
        fontWeight: 'bold',
        fontSize: 19,
        lineHeight: '24px',
        letterSpacing: '0.4px'
    },
    smallSubtitle: {
        fontSize: 12,
        lineHeight: '16px',
        letterSpacing: '0.1px'
    },
    link: {
        fontWeight: '600',
        fontSize: 14,
        lineHeight: '20px',
        letterSpacing: '0.2px',
        color: color.lightBlue,
        textAlign: 'right',
        cursor: 'pointer',
        textDecoration: 'underline',
        '&:hover': {
            color: color.paleBlue
        }
    },
    itemTitle: {
        fontWeight: 600,
        fontSize: 14,
        lineHeight: '20px',
        letterSpacing: 0.2
    },
    title: {
        fontWeight: 'bold',
        fontSize: 24,
        color: color.brightBlue, 
        lineHeight: '30px',
        letterSpacing: 0.3
    }
};

export default {
    // https://www.colorhexa.com/A4A6B3
    color,
    typography
};
