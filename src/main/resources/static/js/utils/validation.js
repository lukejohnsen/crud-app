function validatePersonForm(event) {
    const errors = [];
    const firstName = document.getElementById('firstName').value.trim();
    const lastName = document.getElementById('lastName').value.trim();
    const emailAddress = document.getElementById('emailAddress').value.trim();
    const streetAddress = document.getElementById('streetAddress').value.trim();
    const city = document.getElementById('city').value.trim();
    const state = document.getElementById('state').value.trim();
    const zipCode = document.getElementById('zipCode').value.trim();

    if (firstName.length === 0 || firstName.length > 100) {
        errors.push('Contact first name is required with a maximum length of 100');
    }

    if (lastName.length === 0 || lastName.length > 100) {
        errors.push('Contact last name is required with a maximum length of 100');
    }

    if (emailAddress.length === 0 || emailAddress.length > 100) {
        errors.push('Contact email address is required with a maximum length of 100');
    }

    if (streetAddress.length === 0 || streetAddress.length > 100) {
        errors.push('Contact street address is required with a maximum length of 100');
    }

    if (city.length === 0 || city.length > 100) {
        errors.push('Contact city is required with a maximum length of 100');
    }

    if (state.length !== 2) {
        errors.push('Contact state abbreviation is required with a length of two');
    }

    if (zipCode.length !== 5) {
        errors.push('Zip code is required with a length 5');
    }

    return displayErrors(errors, event);
}

function validateClientForm(event) {
    const errors = [];
    const companyName = document.getElementById('companyName').value.trim();
    const websiteUri = document.getElementById('websiteUri').value.trim();
    const phoneNumber = document.getElementById('phoneNumber').value.trim();
    const streetAddress = document.getElementById('streetAddress').value.trim();
    const city = document.getElementById('city').value.trim();
    const state = document.getElementById('state').value.trim();
    const zipCode = document.getElementById('zipCode').value.trim();

    if (companyName.length === 0 || companyName.length > 100) {
        errors.push('Company name is required with a maximum length of 100');
    }

    if (websiteUri.length === 0 || websiteUri.length > 100) {
        errors.push('Company website URI is required has a maximum length of 100');
    }

    if (phoneNumber.length === 0 || phoneNumber.length > 20) {
        errors.push('Company phone number is require has a maximum length of 20');
    }

    if (streetAddress.length === 0 || streetAddress.length > 100) {
        errors.push('Company address is required and has a maximum length of 100');
    }

    if (city.length > 0 || city.length > 100) {
        errors.push('Company city is required and has a maximum length of 100');
    }

    if (state.length !== 2) {
        errors.push('Company state abbreviation must be two characters');
    }

    if (zipCode.length !== 5) {
        errors.push('Zip code is required with length 5');
    }

    return displayErrors(errors, event);
}

function displayErrors(errors, event) {
    const errorContainer = document.getElementById('errorContainer');

    if (errors.length > 0) {
        event.preventDefault();

        if (errorContainer) {
            errorContainer.innerHTML =
                '<div class="bg-red-50 border-l-4 border-red-400 p-4 mb-6">' +
                '<p class="font-semibold text-red-800 mb-2">Please correct the following errors in your submission:</p>' +
                '<ul class="list-disc list-inside text-red-700">' +
                errors.map(error => '<li>' + error + '</li>').join('') +
                '</ul></div>';
            window.scrollTo(0, 0);
        }

        return false;
    } else {
        if (errorContainer) {
            errorContainer.innerHTML = '';
        }
        return true;
    }
}