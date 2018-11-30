package pl.devwannabe.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidateGroupFirst.class, ValidateGroupSecond.class})
public class ValidateGroupSequence {

}