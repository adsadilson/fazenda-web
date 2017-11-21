function closeDialog(xhr, status, args) {
	if (! args.validationFailed && ! args.exceptionThrown ) {
		editionDialog.hide();
	}
}