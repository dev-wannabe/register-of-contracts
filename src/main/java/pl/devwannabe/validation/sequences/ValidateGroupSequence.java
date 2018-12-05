package pl.devwannabe.validation.sequences;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidateGroupFirst.class, ValidateGroupSecond.class})
public class ValidateGroupSequence {

}