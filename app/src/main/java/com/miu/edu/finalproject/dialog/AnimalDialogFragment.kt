package com.miu.edu.finalproject.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.miu.edu.finalproject.R
import com.miu.edu.finalproject.data.model.Animal
import com.miu.edu.finalproject.databinding.AnimalDialogBinding
import com.miu.edu.finalproject.utils.onErrorTextFieldValidator
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty

class AnimalDialogFragment(private val itemClicked: OnAddAnimalItemClicked) : AppCompatDialogFragment(), Validator.ValidationListener {

    private lateinit var binding: AnimalDialogBinding
    private lateinit var validator: Validator

    @NotEmpty(message = "Name Field is required")
    lateinit var nameEditText: TextInputEditText

    @NotEmpty(message = "Habitat Field is required")
    lateinit var habitatEditText: TextInputEditText

    @NotEmpty(message = "Diet Field is required")
    lateinit var dietEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AnimalDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * Initializing fields
        * */
        initializingFields()

        validator = Validator(this)
        validator.setValidationListener(this)

        binding.saveTextView.setOnClickListener {
            validator.validate()
        }

        binding.cancelTextView.setOnClickListener {
            dismiss()
        }
    }

    private fun initializingFields() {
        nameEditText = binding.nameEditText
        habitatEditText = binding.habitatEditText
        dietEditText = binding.dietEdit
    }

    override fun onValidationSucceeded() {
        val name = nameEditText.text.toString()
        val habitat = habitatEditText.text.toString()
        val diet = dietEditText.text.toString()
        itemClicked.onAnimalItemClick(
            Animal(
            name,habitat,diet
        ), dialog)
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        activity?.onErrorTextFieldValidator(errors)
    }
}

interface OnAddAnimalItemClicked{
    fun onAnimalItemClick(animal: Animal, dialog: Dialog?)
}